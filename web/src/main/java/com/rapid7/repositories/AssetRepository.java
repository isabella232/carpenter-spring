/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.repositories;

import com.rapid7.model.Asset;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Asset repository backed by Spring Data.
 */
public interface AssetRepository extends PagingAndSortingRepository<Asset, Long>
{
   // additional custom finder methods go here
}
