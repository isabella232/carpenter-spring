/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.carpenter;

import org.springframework.data.domain.Page;
import java.util.List;

/**
 * Provides structure for returning JSON results to carpenter.
 */
public class CarpenterPageResult<T>
{
   /**
    * Constructs an initialized page for Carpenter.
    * @param collection The collection of items that are on the current page.
    * @param totalPages The total number of pages available.
    * @param totalCount The total number of items available.
    */
   public CarpenterPageResult(List<T> collection, int totalPages, long totalCount)
   {
      this.collection = collection;
      this.total_pages = totalPages;
      this.total_count = totalCount;
   }

   /**
    * Constructs an initialized page for Carpenter.
    * @param page The resulting page from a Spring Data query
    */
   public CarpenterPageResult(Page<T> page)
   {
      this.collection = page.getContent();
      this.total_pages = page.getTotalPages();
      this.total_count = page.getTotalElements();
   }

   /**
    * @return The list of items on the current page.
    */
   public List<T> getCollection()
   {
      return collection;
   }

   /**
    * @return The total number of pages.
    */
   public int getTotal_pages()
   {
      return total_pages;
   }

   /**
    * @return The total number of items.
    */
   public long getTotal_count()
   {
      return total_count;
   }

   private List<T> collection;
   private int total_pages;
   private long total_count;
}
