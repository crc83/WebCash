package com.siriusif.webcash.admin.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.siriusif.model.discount.BasicDiscount;

/**
 * Backing bean for BasicDiscount entities.
 * <p>
 * This class provides CRUD functionality for all BasicDiscount entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class BasicDiscountBean implements Serializable
{

   private static final long serialVersionUID = 1L;

   /*
    * Support creating and retrieving BasicDiscount entities
    */

   private Long id;

   public Long getId()
   {
      return this.id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   private BasicDiscount basicDiscount;

   public BasicDiscount getBasicDiscount()
   {
      return this.basicDiscount;
   }

   @Inject
   private Conversation conversation;

   @PersistenceContext(type = PersistenceContextType.EXTENDED)
   private EntityManager entityManager;

   public String create()
   {

      this.conversation.begin();
      return "create?faces-redirect=true";
   }

   public void retrieve()
   {

      if (FacesContext.getCurrentInstance().isPostback())
      {
         return;
      }

      if (this.conversation.isTransient())
      {
         this.conversation.begin();
      }

      if (this.id == null)
      {
         this.basicDiscount = this.example;
      }
      else
      {
         this.basicDiscount = findById(getId());
      }
   }

   public BasicDiscount findById(Long id)
   {

      return this.entityManager.find(BasicDiscount.class, id);
   }

   /*
    * Support updating and deleting BasicDiscount entities
    */

   public String update()
   {
      this.conversation.end();

      try
      {
         if (this.id == null)
         {
            this.entityManager.persist(this.basicDiscount);
            return "search?faces-redirect=true";
         }
         else
         {
            this.entityManager.merge(this.basicDiscount);
            return "view?faces-redirect=true&id=" + this.basicDiscount.getId();
         }
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   public String delete()
   {
      this.conversation.end();

      try
      {
         this.entityManager.remove(findById(getId()));
         this.entityManager.flush();
         return "search?faces-redirect=true";
      }
      catch (Exception e)
      {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
         return null;
      }
   }

   /*
    * Support searching BasicDiscount entities with pagination
    */

   private int page;
   private long count;
   private List<BasicDiscount> pageItems;

   private BasicDiscount example = new BasicDiscount();

   public int getPage()
   {
      return this.page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getPageSize()
   {
      return 10;
   }

   public BasicDiscount getExample()
   {
      return this.example;
   }

   public void setExample(BasicDiscount example)
   {
      this.example = example;
   }

   public void search()
   {
      this.page = 0;
   }

   public void paginate()
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

      // Populate this.count

      CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
      Root<BasicDiscount> root = countCriteria.from(BasicDiscount.class);
      countCriteria = countCriteria.select(builder.count(root)).where(
            getSearchPredicates(root));
      this.count = this.entityManager.createQuery(countCriteria)
            .getSingleResult();

      // Populate this.pageItems

      CriteriaQuery<BasicDiscount> criteria = builder.createQuery(BasicDiscount.class);
      root = criteria.from(BasicDiscount.class);
      TypedQuery<BasicDiscount> query = this.entityManager.createQuery(criteria
            .select(root).where(getSearchPredicates(root)));
      query.setFirstResult(this.page * getPageSize()).setMaxResults(
            getPageSize());
      this.pageItems = query.getResultList();
   }

   private Predicate[] getSearchPredicates(Root<BasicDiscount> root)
   {

      CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
      List<Predicate> predicatesList = new ArrayList<Predicate>();

      String name = this.example.getName();
      if (name != null && !"".equals(name))
      {
         predicatesList.add(builder.like(root.<String> get("name"), '%' + name + '%'));
      }

      return predicatesList.toArray(new Predicate[predicatesList.size()]);
   }

   public List<BasicDiscount> getPageItems()
   {
      return this.pageItems;
   }

   public long getCount()
   {
      return this.count;
   }

   /*
    * Support listing and POSTing back BasicDiscount entities (e.g. from inside an
    * HtmlSelectOneMenu)
    */

   public List<BasicDiscount> getAll()
   {

      CriteriaQuery<BasicDiscount> criteria = this.entityManager
            .getCriteriaBuilder().createQuery(BasicDiscount.class);
      return this.entityManager.createQuery(
            criteria.select(criteria.from(BasicDiscount.class))).getResultList();
   }

   @Resource
   private SessionContext sessionContext;

   public Converter getConverter()
   {

      final BasicDiscountBean ejbProxy = this.sessionContext.getBusinessObject(BasicDiscountBean.class);

      return new Converter()
      {

         @Override
         public Object getAsObject(FacesContext context,
               UIComponent component, String value)
         {

            return ejbProxy.findById(Long.valueOf(value));
         }

         @Override
         public String getAsString(FacesContext context,
               UIComponent component, Object value)
         {

            if (value == null)
            {
               return "";
            }

            return String.valueOf(((BasicDiscount) value).getId());
         }
      };
   }

   /*
    * Support adding children to bidirectional, one-to-many tables
    */

   private BasicDiscount add = new BasicDiscount();

   public BasicDiscount getAdd()
   {
      return this.add;
   }

   public BasicDiscount getAdded()
   {
      BasicDiscount added = this.add;
      this.add = new BasicDiscount();
      return added;
   }
}