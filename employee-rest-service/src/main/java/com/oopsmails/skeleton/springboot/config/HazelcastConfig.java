package com.oopsmails.skeleton.springboot.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@ImportAutoConfiguration({HazelcastAutoConfiguration.class, CacheAutoConfiguration.class})
public class HazelcastConfig {

    @Autowired
    private HazelcastCustomProperties hazelcastCustomProperties;

    @Bean
    // Error: with method name hazelcastConfig(), factory-bean reference points back to the same bean definition
    // resolved by changing method name, should not be the same as class name.
    public Config hazelcastConfigCustom() {
        Config config = new Config();
        config.setLicenseKey(hazelcastCustomProperties.getLicense());
        config.setProperty("hazelcast.logging.type", "slf4j");
        configureInstance(config);
        configureDiscovery(config);
        configureCaches(config);
        return config;
    }

    private void configureCaches(Config config) {
        config.addMapConfig(new MapConfig("employeeRepositoryCache").setTimeToLiveSeconds(1 * 60)
                .setMaxSizeConfig(new MaxSizeConfig(100, MaxSizeConfig.MaxSizePolicy.PER_PARTITION))
                .setEvictionPolicy(EvictionPolicy.LRU));
    }

    protected void configureDiscovery(Config config) {
        config.getNetworkConfig().getJoin().getAwsConfig().setEnabled(false);
        if (hazelcastCustomProperties.isMulticastEnabled()) {
            config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);
            config.getNetworkConfig().getJoin().getMulticastConfig()
                    .setEnabled(true)
                    .setMulticastGroup(hazelcastCustomProperties.getMulticastGroup())
                    .setMulticastPort(hazelcastCustomProperties.getMulticastPort());
        } else {
            // if multicast is not enabled, use tcpip
            config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
            config.getNetworkConfig().getJoin().getTcpIpConfig()
                    .setEnabled(true)
                    .addMember(hazelcastCustomProperties.getTcpIpMember());
        }
    }

    protected void configureInstance(Config config) {

        if (StringUtils.hasText(hazelcastCustomProperties.getGroupName())) {
            config.getGroupConfig().setName(hazelcastCustomProperties.getGroupName());
        }
        if (StringUtils.hasText(hazelcastCustomProperties.getGroupPassword())) {
            config.getGroupConfig().setPassword(hazelcastCustomProperties.getGroupName());
        }
        config.getNetworkConfig().setPort(hazelcastCustomProperties.getPort()).setPortAutoIncrement(true);
    }
}
