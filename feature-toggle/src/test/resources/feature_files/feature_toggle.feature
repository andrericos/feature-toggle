#language:en
#author:andrericos

Feature: Feature Toggle Tests

  Scenario Template: Main feature toggle scenario
    Given a implementation of this interface called "main" with parameter toggle called 'paramTest'
    Given a implementation of this interface called "newImplementation" with parameter toggle called 'paramTest'
    Given the feature toggle called "featureName" switching between:
      | originalFeature | main              |
      | newFeature      | newImplementation |
    When those features are in the featureNames at configuration "<features>"
    When those features activates with those parameters "<featureParameters>"
    When the service is called with class parameter "<parameter>"
    Then the implementation of operation(OperationParameter) "<invokedImplementation>" will be used

    Examples:
      | features           | featureParameters            | parameter | invokedImplementation |
      |                    |                              | 2         | main                  |
      | xicote             |                              | 2         | main                  |
      | featureName        |                              | 2         | newImplementation     |
      | featureName,xicote |                              | 2         | newImplementation     |
      | xicote,featureName |                              | 2         | newImplementation     |
      |                    | paramTest=1                  | 2         | main                  |
      |                    | paramTest=1                  | 1         | main                  |
      | xicote             | paramTest=1                  | 2         | main                  |
      | featureName        | paramTest=1                  | 2         | main                  |
      | featureName        | paramTest=1                  | 1         | newImplementation     |
      | featureName        | paramTest=                   | 2         | newImplementation     |
      | featureName        | paramTest=1                  | 2         | main                  |
      | featureName        | wrongfeatureName&paramTest=1 | 2         | newImplementation     |
      | featureName        | featureName&paramTest=1      | 2         | main                  |
      | featureName        | featureName&paramTest=1      | 1         | newImplementation     |
      | featureName,xicote |                              |           | newImplementation     |
      | xicote,featureName |                              |           | newImplementation     |

  Scenario Template: Switchabrio
    Given a implementation of this interface called "main" with parameter toggle called 'paramTest'
    And a implementation of this interface called "newImplementation_feature1" with parameter toggle called 'paramTest'
    And a implementation of this interface called "newImplementation_feature2" with parameter toggle called 'paramTest'
    And a implementation of this interface called "newImplementation_feature3" with parameter toggle called 'paramTest'
    And the Switchable feature toggle for alternative of the implementation called "main" and this cases:
      | featureName | implementationName         |
      | feature1    | newImplementation_feature1 |
      | feature2    | newImplementation_feature2 |
      | feature3    | newImplementation_feature3 |
    When those features are in the featureNames at configuration "<features>"
    And those features activates with those parameters "<featureParameters>"
    And the service is called with parameter "<parameter>"
    Then the implementation of operation(String) "<invokedImplementation>" will be used

    Examples:
      | features          | featureParameters                         | parameter | invokedImplementation      |
      |                   |                                           | 1         | main                       |
      | feature1,xico     |                                           | 1         | newImplementation_feature1 |
      | feature2          |                                           | 1         | newImplementation_feature2 |
      | feature3          |                                           | 1         | newImplementation_feature3 |
      | feature3,feature1 |                                           | 1         | newImplementation_feature1 |
      | feature2,feature1 |                                           | 1         | newImplementation_feature1 |
      | feature2,feature3 |                                           | 1         | newImplementation_feature2 |
      | feature2,feature3 | feature2&paramTest=1                      | 1         | newImplementation_feature2 |
      | feature2,feature3 | feature2&paramTest=2                      | 1         | newImplementation_feature3 |
      | feature2,feature3 | feature2&paramTest=2;feature3&paramTest=3 | 1         | main                       |
