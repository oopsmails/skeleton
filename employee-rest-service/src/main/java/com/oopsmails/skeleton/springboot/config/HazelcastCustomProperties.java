package com.oopsmails.skeleton.springboot.config;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:testprops.properties")
@ConfigurationProperties(prefix = "employee.service.hazelcast")
@Data
public class HazelcastCustomProperties {
    @Getter
    @Setter
    private Map<String, String> override = new HashMap<>();

    @Getter
    @Setter
    private int port;

    @Getter
    @Setter
    private String license;

    @Value("${employee.service.hazelcast.multicast.enabled}")
    @Getter
    @Setter
    private boolean multicastEnabled;

    @Value("${employee.service.hazelcast.multicast.group}")
    @Getter
    @Setter
    private String multicastGroup;

    @Value("${employee.service.hazelcast.multicast.port}")
    @Getter
    @Setter
    private int multicastPort;

    @Value("${employee.service.hazelcast.tcpip.member}")
    @Getter
    @Setter
    private String tcpIpMember;

    @Value("${employee.service.hazelcast.group.name}")
    @Getter
    @Setter
    private String groupName;

    @Value("${employee.service.hazelcast.group.password}")
    @Getter
    @Setter
    private String groupPassword;


}
