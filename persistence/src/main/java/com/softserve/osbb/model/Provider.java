package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cavayman on 05.07.2016.
 */
@Entity
@Table(name = "provider")
public class Provider implements Comparable{
    private Integer providerId;
    private String name;
    private String description;
    private String logourl;
    private Collection<Contract> contractsByProviderId;

    public Provider() {
    }

    public Provider(String name) {
        this.name = name;
    }

    public Provider(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "logourl")
    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "providerByProviderId")
    public Collection<Contract> getContractsByProviderId() {
        return contractsByProviderId;
    }

    public void setContractsByProviderId(Collection<Contract> contractsByProviderId) {
        this.contractsByProviderId = contractsByProviderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provider that = (Provider) o;

        if (providerId != null ? !providerId.equals(that.providerId) : that.providerId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (logourl != null ? !logourl.equals(that.logourl) : that.logourl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerId != null ? providerId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (logourl != null ? logourl.hashCode() : 0);
        return result;
    }


    //natural order by provider's names
    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Provider) o).getName());
    }
}
