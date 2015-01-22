package de.zalando.zmon.eventlogservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jmussler on 1/22/15.
 */
@EnableAutoConfiguration
@Configuration
public class StoreFactory {

    @Value(value="${cassandra.host:null}")
    String cassandraHost;

    @Value("${cassandra.port:0}")
    int cassandraPort;

    @Value("${cassandra.keyspace:null}")
    String cassandraKeyspace;

    @Value("${postgresql.host:localhost}")
    String postgresqlHost;

    @Value("${postgresql.port:5432}")
    int postgresqlPort;

    @Value("${postgresql.database:zmon_local_db}")
    String postgresqlDatabase;

    @Value("${postgresql.user:postgres}")
    String postgresqlUser;

    @Value("${postgresql.password}")
    String postgresqlPassword;

    @Value("${postgresql.schema:zmon_eventlog}")
    String postgresqlSchema;

    @Bean
    EventStore getStore() {
        if(cassandraHost!=null) {
            return new PostgresqlStore(postgresqlHost, postgresqlPort, postgresqlDatabase, postgresqlUser, postgresqlPassword, postgresqlSchema);
        }
        else {
            return new CassandraStore(cassandraHost, cassandraPort, cassandraKeyspace);
        }
    }
}
