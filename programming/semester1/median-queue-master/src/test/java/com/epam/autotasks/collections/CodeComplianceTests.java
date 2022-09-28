package com.epam.autotasks.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtField;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.AbstractQueue;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeComplianceTests {

    @Test
    void testMedianQueueAncestor() {
        assertEquals(AbstractQueue.class, MedianQueue.class.getSuperclass());
    }

    @Test
    void testNoListsOrSetsAsFields() {
        Set<CtField> listsSetsFields = spoon.getModel().getElements(new TypeFilter<>(CtField.class)).stream()
                .filter(CodeComplianceTests::isCollectionAndSetOrList)
                .collect(Collectors.toSet());
        assertEquals(0, listsSetsFields.size(), "You must not use Lists or Sets, but you have provided " + listsSetsFields);
    }
    
    @Test
    void testNoArraysAsFields() {
        Set<CtField> arrayFields = spoon.getModel().getElements(new TypeFilter<>(CtField.class)).stream()
                .filter(ctField -> ctField.getType().isArray())
                .collect(Collectors.toSet());
        assertEquals(0, arrayFields.size(), "You must not use arrays, but you have provided " + arrayFields);
    }

    @Test
    void testNoListsOrSetsAsExpressions() {
        Set<CtExpression> listsSetsExpressions = spoon.getModel().getElements(new TypeFilter<>(CtExpression.class)).stream()
                .filter(CodeComplianceTests::isCollectionAndSetOrList)
                .collect(Collectors.toSet());
        assertEquals(0, listsSetsExpressions.size(), "You must not use Lists or Sets, but you have provided " + listsSetsExpressions);
    }

    @Test
    void testNoArraysAsExpressions() {
        Set<CtExpression> arrayExpressions = spoon.getModel().getElements(new TypeFilter<>(CtExpression.class)).stream()
                .filter(ctExpression -> ctExpression.getType() != null)
                .filter(ctExpression -> ctExpression.getType().isArray())
                .collect(Collectors.toSet());
        assertEquals(0, arrayExpressions.size(), "You must not use arrays, but you have provided " + arrayExpressions);
    }

    private static boolean isCollectionAndSetOrList(CtField<?> ctField) {
        CtTypeReference fieldType = ctField.getType();
        return fieldType.isSubtypeOf(collectionTypeRef)
                && (fieldType.isSubtypeOf(listTypeRef) || fieldType.isSubtypeOf(setTypeRef));
    }

    private static boolean isCollectionAndSetOrList(CtExpression<?> ctExpression) {
        CtTypeReference expType = ctExpression.getType();
        return expType != null && expType.isSubtypeOf(collectionTypeRef)
                && (expType.isSubtypeOf(listTypeRef) || expType.isSubtypeOf(setTypeRef));
    }

    private static SpoonAPI spoon;
    private static CtTypeReference<List> listTypeRef;
    private static CtTypeReference<Set> setTypeRef;
    private static CtTypeReference<Collection> collectionTypeRef;

    @BeforeAll
    static void setupSpoon() {
        spoon = new Launcher();
        spoon.addInputResource("src/main/java/");
        spoon.buildModel();

        listTypeRef = spoon.getFactory().Type().createReference(List.class);
        setTypeRef = spoon.getFactory().Type().createReference(Set.class);
        collectionTypeRef = spoon.getFactory().Type().createReference(Collection.class);
    }

}