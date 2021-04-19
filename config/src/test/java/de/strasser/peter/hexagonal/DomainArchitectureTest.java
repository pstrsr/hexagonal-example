package de.strasser.peter.hexagonal;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "de.strasser.peter.hexagonal")
public class DomainArchitectureTest {

  @ArchTest
  static final ArchRule should_OnlyDependOnLombokAndExceptions =
      classes()
          .that()
          .resideInAPackage("..domain..")
          .should()
          .onlyDependOnClassesThat()
          .resideInAnyPackage("..lombok..", "..domain..", "java..", "..exception..");
}
