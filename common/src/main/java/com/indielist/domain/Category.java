package com.indielist.domain;

import javax.persistence.*;

/**
 * @author jsingh on 15-01-17.
 */
@Entity
@Table(name="roles")
@NamedQueries({
        @NamedQuery(name = "Category.findCategoryByName", query = "SELECT c FROM Category c WHERE c.name = :name")
})
public class Category extends BaseEntity {

    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static Category findCategoryByName(String name) {
        return (Category) getSingleResultOrNull(em().createNamedQuery("Category.findCategoryByName").setParameter("name", name));
    }
}
