package com.whiz.clients.statsservice.proxy;

import com.whiz.clients.statsservice.controller.dto.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "client-service", url="${proxy.client-service.url}")
public interface ClientProxy {
    @GetMapping("/client")
    ResponseEntity<List<ClientDTO>> getAll();
}
