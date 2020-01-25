package de.htwsaar.sar.library;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ArchitectureTests {

    @Test
    void Classes_that_are_annotated_with_Entity_should_reside_in_package_domain() {
        JavaClasses importedClasses = new ClassFileImporter().importClasspath();

        ArchRule rule = classes()
                .that().areAnnotatedWith(Entity.class)
                .should().resideInAPackage("..domain..");

        rule.check(importedClasses);
    }
}
