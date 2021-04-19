package de.strasser.peter.hexagonal;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = "de.strasser.peter.hexagonal")
public class HexagonalArchitectureTest {

  /**
   * See diagram at https://www.archunit.org/userguide/html/000_Index.html#Onion%20Architecture for
   * what rules this enforces.
   */
  @ArchTest
  static final ArchRule should_FollowHexagonalRules =
      onionArchitecture()
          .domainModels("de.strasser.peter.hexagonal.application.domain..")
          .domainServices("de.strasser.peter.hexagonal.application.service..")
          .applicationServices("de.strasser.peter.hexagonal..")
          .adapter("web", "de.strasser.peter.hexagonal.web..")
          .adapter("persistence", "de.strasser.peter.hexagonal.persistence..")
          .adapter("addressvalidation", "de.strasser.peter.hexagonal.addressvalidation..");
}
