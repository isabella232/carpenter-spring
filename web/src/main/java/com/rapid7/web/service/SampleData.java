/***************************************************************************
 * COPYRIGHT (C) 2014, Rapid7 LLC, Boston, MA, USA.
 * All rights reserved. This material contains unpublished, copyrighted
 * work including confidential and proprietary information of Rapid7.
 **************************************************************************/

package com.rapid7.web.service;

/**
 * TODO: Describe Me!
 */
public class SampleData
{
   protected static final int ASSET_COUNT = 1000;
   protected static final String[] OWNERS =
      {
         "wsharar",
         "chlee",
         "jmartin",
         "vmacdougal",
         "dkumar",
         "lguo",
         "nbibnager",
         "ecarey",
         "bwunsch",
         "lvarela"
      };

   protected static String generateAddress(int index)
   {
      return
         Integer.toString(10 + ((index >> 24) & 0x000000ff)) + "." +
            Integer.toString((index >> 16) & 0x000000ff) + "." +
            Integer.toString((index >> 8) & 0x000000ff) + "." +
            Integer.toString(index & 0x000000ff);
   }
}
