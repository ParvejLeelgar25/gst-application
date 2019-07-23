package com.axelor.gst.web;

import java.util.List;

import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class ProductController {

  public void report(ActionRequest request, ActionResponse response) {

    List<Integer> ids;
    if ((ids = (List) request.getContext().get("_ids")) == null) {
      throw new IllegalArgumentException("Please Select Atleast One Record");
    } else {
      String ids_str = ids.toString();
      String productId = ids_str.substring(1, ids_str.length() - 1);
      request.getContext().put("productId", productId);
    }
  }
}
