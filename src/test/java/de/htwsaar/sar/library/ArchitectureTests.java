package de.htwsaar.sar.library;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ArchitectureTests {

    private static final JavaClasses classes = new ClassFileImporter().importPackages("de.htwsaar.sar.library");

    @Test
    void Catalogue_context_should_not_depend_on_lending_context() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..catalogue..")
                .should().dependOnClassesThat().resideInAPackage("..lending..");
        rule.check(classes);
    }

    @Test
    void Domain_should_never_depend_on_application_or_infrastructure() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain")
                .should().dependOnClassesThat().resideInAPackage("..application")
                .orShould().dependOnClassesThat().resideInAPackage("..infrastructure");
        rule.check(classes);
    }

    @Test
    void Domain_should_not_depend_on_Spring() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain")
                .should().dependOnClassesThat().resideInAPackage("org.springframework..");
        rule.check(classes);
    }

    @Test
    void Repositories_should_be_public_and_reside_in_infrastructure_package() {
        ArchRule rule = classes()
                .that().areAnnotatedWith(Repository.class)
                .should().bePublic().andShould().resideInAPackage("..infrastructure");
        rule.check(classes);
    }

    @Test
    void Controllers_should_not_use_repositories_directly() {
        ArchRule rule = noClasses()
                .that().areAnnotatedWith(RestController.class)
                .should().dependOnClassesThat().areAnnotatedWith(Repository.class);
        rule.check(classes);
    }
}
