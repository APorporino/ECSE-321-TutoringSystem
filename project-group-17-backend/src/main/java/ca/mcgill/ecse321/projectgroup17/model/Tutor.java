package ca.mcgill.ecse321.projectgroup17.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("tutor")
public class Tutor extends Person{



}
