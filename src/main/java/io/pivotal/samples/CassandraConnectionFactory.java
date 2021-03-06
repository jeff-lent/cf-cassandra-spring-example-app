package io.pivotal.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;

public class CassandraConnectionFactory  {

    @Autowired
    CassandraProperties cassandraProperties;

    CloudCassandraPropertiesManager cloudConfig;

    CassandraProperties activeProperties;

    public CassandraConnectionFactory() {
        cloudConfig = new CloudCassandraPropertiesManager();
    }

    private void setActiveProperty() {
        if (cloudConfig.hasCloudProperties()) {
            activeProperties = cloudConfig;
        } else {
            activeProperties = cassandraProperties;
        }
    }

    public CassandraProperties getProperties() {
        if (activeProperties == null) setActiveProperty();
        return activeProperties;
    }

}
