/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.web.controllers;

import com.rapid7.carpenter.CarpenterPageableBuilder;
import com.rapid7.model.Asset;
import com.rapid7.carpenter.CarpenterPageResult;
import com.rapid7.repositories.AssetRepository;
import com.rapid7.web.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC Controller to handle requests from Carpenter
 */
@Controller
public class CarpenterController
{
   @Autowired
   private AssetService assetService;

   @Autowired
   private AssetRepository assetRepository;

   @RequestMapping("/assets")
   public  @ResponseBody
   CarpenterPageResult<Asset> requestAssets(
      @RequestParam(value="page", required=false, defaultValue="1") String page,
      @RequestParam(value="per_page", required=false, defaultValue="20") String perPage,
      @RequestParam(value="sort_by", required=false) String sortBy)
   {
      return assetService.find(page, perPage, sortBy);
   }

   @RequestMapping("/data/assets")
   public  @ResponseBody
   CarpenterPageResult<Asset> requestDataAssets(
      @RequestParam(value="page", required=false, defaultValue="1") String page,
      @RequestParam(value="per_page", required=false, defaultValue="20") String perPage,
      @RequestParam(value="sort_by", required=false) String sortBy)
   {
      return new CarpenterPageResult<>(assetRepository.findAll(
         new CarpenterPageableBuilder()
            .page(page)
            .perPage(perPage)
            .sortBy(sortBy)
            .builld()));
   }
}
