/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A simple sample asset
 */
@Entity
public class Asset
{
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Long id;
   private String hostName;
   private String ipAddress;
   private String owner;

   public Asset()
   {
   }

   public Asset(String hostName, String ipAddress, String owner)
   {
      this.hostName = hostName;
      this.ipAddress = ipAddress;
      this.owner = owner;
   }

   public Long getId()
   {
      return id;
   }

   public String getHostName()
   {
      return hostName;
   }

   public String getIpAddress()
   {
      return ipAddress;
   }

   public String getOwner()
   {
      return owner;
   }
}
