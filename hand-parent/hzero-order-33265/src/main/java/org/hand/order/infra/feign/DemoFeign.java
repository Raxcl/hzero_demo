package org.hand.order.infra.feign;

import org.springframework.cloud.openfeign.FeignClient;


/**
 * FeignDemo
 * @author 33265
 */
@FeignClient(value = "demo-service", path = "/v1/demos")
public interface DemoFeign {
}
