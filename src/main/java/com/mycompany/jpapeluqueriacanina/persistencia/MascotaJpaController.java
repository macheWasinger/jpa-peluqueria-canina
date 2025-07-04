package com.mycompany.jpapeluqueriacanina.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.jpapeluqueriacanina.logica.Duenio;
import com.mycompany.jpapeluqueriacanina.logica.Mascota;
import com.mycompany.jpapeluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class MascotaJpaController implements Serializable {

    public MascotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public MascotaJpaController() {
        emf = Persistence.createEntityManagerFactory("peluqueriacaninaJPAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascota mascota) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Duenio duenioAsociado = mascota.getDuenioAsociado();
            if (duenioAsociado != null) {
                duenioAsociado = em.getReference(duenioAsociado.getClass(), duenioAsociado.getId());
                mascota.setDuenioAsociado(duenioAsociado);
            }
            em.persist(mascota);
            if (duenioAsociado != null) {
                duenioAsociado.getListaMascota().add(mascota);
                duenioAsociado = em.merge(duenioAsociado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascota mascota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota persistentMascota = em.find(Mascota.class, mascota.getId());
            Duenio duenioAsociadoOld = persistentMascota.getDuenioAsociado();
            Duenio duenioAsociadoNew = mascota.getDuenioAsociado();
            if (duenioAsociadoNew != null) {
                duenioAsociadoNew = em.getReference(duenioAsociadoNew.getClass(), duenioAsociadoNew.getId());
                mascota.setDuenioAsociado(duenioAsociadoNew);
            }
            mascota = em.merge(mascota);
            if (duenioAsociadoOld != null && !duenioAsociadoOld.equals(duenioAsociadoNew)) {
                duenioAsociadoOld.getListaMascota().remove(mascota);
                duenioAsociadoOld = em.merge(duenioAsociadoOld);
            }
            if (duenioAsociadoNew != null && !duenioAsociadoNew.equals(duenioAsociadoOld)) {
                duenioAsociadoNew.getListaMascota().add(mascota);
                duenioAsociadoNew = em.merge(duenioAsociadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = mascota.getId();
                if (findMascota(id) == null) {
                    throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota mascota;
            try {
                mascota = em.getReference(Mascota.class, id);
                mascota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.", enfe);
            }
            Duenio duenioAsociado = mascota.getDuenioAsociado();
            if (duenioAsociado != null) {
                duenioAsociado.getListaMascota().remove(mascota);
                duenioAsociado = em.merge(duenioAsociado);
            }
            em.remove(mascota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mascota> findMascotaEntities() {
        return findMascotaEntities(true, -1, -1);
    }

    public List<Mascota> findMascotaEntities(int maxResults, int firstResult) {
        return findMascotaEntities(false, maxResults, firstResult);
    }

    private List<Mascota> findMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascota.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mascota findMascota(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascota> rt = cq.from(Mascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
