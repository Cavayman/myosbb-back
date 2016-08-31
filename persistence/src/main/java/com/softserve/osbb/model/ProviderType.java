package com.softserve.osbb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Anastasiia Fedorak on 7/20/16.
 */

@Entity
@Table(name = "provider_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProviderType implements Serializable {
    private Integer providerTypeId;
    private String providerTypeName;
    private List<Provider> providers;

    public ProviderType(String providerTypeName) {
        this.providerTypeName = providerTypeName;
    }

    public ProviderType() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_type_id")
    public Integer getProviderTypeId() {
        return providerTypeId;
    }

    public void setProviderTypeId(Integer providerTypeId) {
        this.providerTypeId = providerTypeId;
    }

    @Column(name = "type_name")
    public String getProviderTypeName() {
        return providerTypeName;
    }

    public void setProviderTypeName(String providerTypeName) {
        this.providerTypeName = providerTypeName;
    }

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
