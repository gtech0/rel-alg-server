package com.interpreter.relational.operation;

import com.interpreter.relational.service.RowMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.interpreter.relational.operation.Difference.difference;
import static com.interpreter.relational.util.BasicUtilityMethods.*;

public class Division {
    public static Set<RowMap> division(Pair<String, Set<RowMap>> relation1,
                                       Pair<String, Set<RowMap>> relation2,
                                       List<String> attributes,
                                       int operationNumber
    ) {
        Map<String, Set<RowMap>> relations = Map.ofEntries(relation1, relation2);

        for (String attribute : attributes) {
            returnAttributeIfExist(relations, attribute);
        }

        Pair<String, Set<RowMap>> firstRelation = new ImmutablePair<>("", relation1.getRight());
        Set<RowMap> temp0 = Projection.projection(firstRelation, attributes);
        Set<RowMap> temp1 = CartesianProduct.product(temp0, relation2.getRight());
        Set<RowMap> temp2Data = difference(temp1, firstRelation.getRight(), operationNumber);
        Pair<String, Set<RowMap>> temp2 = new ImmutablePair<>("", temp2Data);
        Set<RowMap> temp3 = Projection.projection(temp2, attributes);
        return difference(Projection.projection(firstRelation, attributes), temp3, operationNumber);
    }
}
