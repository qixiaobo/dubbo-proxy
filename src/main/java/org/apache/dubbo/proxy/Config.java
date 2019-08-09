package org.apache.dubbo.proxy;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;

import org.apache.dubbo.proxy.dao.ServiceMapping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@ConfigurationProperties(prefix = "mapping")
@Configuration
public class Config {


    @Value("${proxy.registry.address}")
    private String registryAddress;

    @Value("${proxy.registry.group}")
    private String group;

    @Value("${proxy.metadata-report.address:}")
    private String metadataAddress;

    private List<Mapping> services;

    public List<Mapping> getServices() {
        return services;
    }

    public void setServices(List<Mapping> services) {
        this.services = services;
    }

    @Bean
    public ServiceMapping getServiceMapping() {
        ServiceMapping serviceMapping = new ServiceMapping();
        serviceMapping.setMappings(services);
        return serviceMapping;
    }

    @Bean
    Registry getRegistry() {
        URL url = URL.valueOf(registryAddress);
        if (StringUtils.isNotEmpty(group)) {
            url = url.addParameter(Constants.GROUP_KEY, group);
        }
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(url);
        return registry;
    }


    public static class Mapping {
        private String name;
        private String interfaze;
        private String group;
        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInterfaze() {
            return interfaze;
        }

        public void setInterfaze(String interfaze) {
            this.interfaze = interfaze;
        }

        public String getGroup() {
            return group;
        }

        public void setGroup(String group) {
            this.group = group;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
