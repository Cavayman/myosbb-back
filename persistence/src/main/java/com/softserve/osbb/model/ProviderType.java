package com.softserve.osbb.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anastasiia Fedorak on 7/20/16.
 */

@Entity
@Table(name = "provider_type")
public class ProviderType {
    private Integer providerTypeId;
    private String providerTypeName;
    private List<Provider> providers;

    public ProviderType(String providerTypeName) {
        this.providerTypeName = providerTypeName;
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

    @OneToMany(mappedBy = "type")
    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
