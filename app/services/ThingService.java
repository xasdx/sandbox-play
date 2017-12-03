package services;

import models.Thing;

import javax.inject.Singleton;

import static java.util.UUID.randomUUID;

@Singleton
public class ThingService {

  public String sayHi() {
    return "hi";
  }

  public Thing randomThing() {
    return new Thing(randomUUID().toString(), "MyThing");
  }
}
