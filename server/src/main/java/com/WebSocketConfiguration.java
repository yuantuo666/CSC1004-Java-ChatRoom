/**
 * WebSocketConfiguration
 *
 * @author yuantuo666 <yuantuo666@gmail.com>
 * @version 2023/02/25
 */
package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfiguration {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}