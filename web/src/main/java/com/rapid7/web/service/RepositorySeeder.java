/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.web.service;

import com.rapid7.model.Asset;
import com.rapid7.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Create sample data in the repository
 */
@Component
public class RepositorySeeder extends SampleData
{
   @Autowired
   AssetRepository assetRepository;

   @PostConstruct
   public void seedRepository()
   {
      List<Asset> assets = new ArrayList<>(ASSET_COUNT);
      for (int i = 0; i < ASSET_COUNT; i++)
      {
         assets.add(new Asset(Integer.toString(i), generateAddress(i), OWNERS[i%OWNERS.length]));
      }
      assetRepository.save(assets);
   }

}
