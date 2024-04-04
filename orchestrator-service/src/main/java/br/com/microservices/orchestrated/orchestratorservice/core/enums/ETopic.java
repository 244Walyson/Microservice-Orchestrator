package br.com.microservices.orchestrated.orchestratorservice.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETopic {

  START_SAGA("start-saga"),
  BASE_ORCHESTRATOR("orchestrator"),
  FINISH_SUCCESS("finish_success"),
  FINSH_FAIL("finish_fail"),
  PRODUCT_VALIDATION_SUCCESS("product-validation-success"),
  PRODUCT_VALIDATION_FAIL("product-validation-fail"),
  PAYMENT_SUCCESS("payment-success"),
  PAYMENT_FAIL("payment-fail"),
  INVENTORY_SUCCESS("inventory-success"),
  INVENTORY_FAIL("inventory-fail"),
  NOTIFY_ENDING("notify-ending");

  private String topic;
}