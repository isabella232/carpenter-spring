/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.carpenter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Buids a Spring Data PageRequest from Carpenter request parameters
 */
public class CarpenterPageableBuilder
{
   private static final int DEFAULT_INDEX = 0;
   private static final int DEFAULT_COUNT = 10;
   private static final int MIN_INDEX = 0;
   private static final int MIN_COUNT = 1;

   private String page;
   private String perPage;
   private String sortBy;

   public CarpenterPageableBuilder page(String page)
   {
      this.page = page;
      return this;
   }

   public CarpenterPageableBuilder perPage(String perPage)
   {
      this.perPage = perPage;
      return this;
   }

   public CarpenterPageableBuilder sortBy(String sortBy)
   {
      this.sortBy = sortBy;
      return this;
   }

   public Pageable builld()
   {
      // convert from 1-indexed to 0-indexed
      int index;
      try
      {
         index = Integer.valueOf(page) - 1;
      }
      catch (NumberFormatException e)
      {
         index = DEFAULT_INDEX;
      }
      if (index < MIN_INDEX) index = DEFAULT_INDEX;

      // check page size (count)
      int count;
      try
      {
         count = Integer.valueOf(perPage);
      }
      catch (NumberFormatException e)
      {
         count = DEFAULT_COUNT;
      }
      if (count < MIN_COUNT) count = DEFAULT_COUNT;

      // determine sort field and order, if any
      Sort sort = null;
      if (sortBy != null && !sortBy.isEmpty())
      {
         String[] tokens = sortBy.split(" ");
         if (tokens.length > 0)
         {
            String field = tokens[0];
            boolean ascending = true;
            if (tokens.length > 1)
            {
               ascending = !tokens[1].startsWith("des");
            }
            sort = new Sort(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, field);
         }
      }

      return new PageRequest(index, count, sort);
   }
}
