package com.fiorellaviaggi.tourscanner.domain;

public class PageStructureChangedException extends RuntimeException
{
  public PageStructureChangedException(String message)
  {
    super(message);
  }
}
