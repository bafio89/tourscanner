package com.fiorellaviaggi.tourscanner.domain;

import java.util.Objects;

public class Nation
{
  private final Integer id;
  private final String name;

  public Nation(Integer id, String name)
  {
    this.id = id;
    this.name = name;
  }

  public String getName()
  {
    return name;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Nation nation = (Nation) o;
    return Objects.equals(id, nation.id) &&
      Objects.equals(name, nation.name);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, name);
  }
}
