package com.softserve.osbb.model;

import com.softserve.osbb.model.enums.ProviderType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    private String logoUrl;
    private Collection<Contract> contracts;
    private Collection<Bill> bills;
    private ProviderType type;

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

    @Column(name = "logoUrl")
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider")
    public Collection<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Collection<Contract> contracts) {
        this.contracts = contracts;
    }

    @OneToMany(mappedBy = "provider")
    public Collection<Bill> getBills() {
        return bills;
    }

    public void setBills(Collection<Bill> bills) {
        this.bills = bills;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    public ProviderType getType(){
        return type;
    }

    public void setType(ProviderType type){
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }


    //natural order by provider's names
    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Provider) o).getName());
    }
}
