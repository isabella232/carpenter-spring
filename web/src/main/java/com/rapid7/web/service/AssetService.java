/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.web.service;

import com.rapid7.carpenter.CarpenterPageableBuilder;
import com.rapid7.model.Asset;
import com.rapid7.carpenter.CarpenterPageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * A simple service for simple assets.
 */
@Service
public class AssetService extends SampleData
{
   private static List<Asset> assets = new ArrayList<>(ASSET_COUNT);

   static
   {
      for (int i = 0; i < ASSET_COUNT; i++)
      {
         assets.add(new Asset(Integer.toString(i), generateAddress(i), OWNERS[i%OWNERS.length]));
      }
   }

   public CarpenterPageResult<Asset> find(String page, String perPage, String sortBy)
   {
      final Pageable pageRequest = new CarpenterPageableBuilder().page(page).perPage(perPage).sortBy(sortBy).builld();

      Asset[] sorted = new Asset[ASSET_COUNT];
      for (int i = 0; i < ASSET_COUNT; i++)
      {
         sorted[i] = assets.get(i);
      }

      Sort sort = pageRequest.getSort();
      if (sort != null)
      {
         for (Sort.Order order : sort)
         {
            sort(sorted, order.getProperty(), order.getDirection());
         }
      }

      int pageSize = pageRequest.getPageSize();
      int offset = pageRequest.getOffset();
      List<Asset> results = new ArrayList<>(pageSize);
      for (int i = 0; i < pageSize && offset+i < ASSET_COUNT; i++)
      {
         results.add(sorted[offset+i]);
      }

      return new CarpenterPageResult<>(results, ASSET_COUNT/pageSize, ASSET_COUNT);
   }

   private void sort(Asset[] sorted, String field, final Sort.Direction direction)
   {
      switch (field)
      {
         case "hostName":
            Arrays.sort(sorted, new Comparator<Asset>()
            {
               @Override
               public int compare(Asset o1, Asset o2)
               {
                  return direction == Sort.Direction.ASC ?
                         o1.getHostName().compareTo(o2.getHostName()) :
                         o2.getHostName().compareTo(o1.getHostName());
               }
            });
            break;
         case "ipAddress":
            Arrays.sort(sorted, new Comparator<Asset>()
            {
               @Override
               public int compare(Asset o1, Asset o2)
               {
                  return direction == Sort.Direction.ASC ?
                         o1.getIpAddress().compareTo(o2.getIpAddress()) :
                         o2.getIpAddress().compareTo(o1.getIpAddress());
               }
            });
            break;
         case "owner":
            Arrays.sort(sorted, new Comparator<Asset>()
            {
               @Override
               public int compare(Asset o1, Asset o2)
               {
                  return direction == Sort.Direction.ASC ?
                         o1.getOwner().compareTo(o2.getOwner()) :
                         o2.getOwner().compareTo(o1.getOwner());
               }
            });
            break;
      }
   }

}
