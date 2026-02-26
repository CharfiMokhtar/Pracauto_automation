pipeline {
    agent any

    environment {
        TOKEN = credentials('TOKEN')
    }

    parameters {
        string(name: 'TEST_PLAN', defaultValue: 'POEI25G1P2-27', description: 'Clé du plan de test Xray')
        string(name: 'KEYS', defaultValue: 'POEI25G1P2-31;POEI25G1P2-56', description: 'Clé(s) des tests à exporter depuis Xray (séparées par des ;)')
        booleanParam(name: 'HEADLESS', defaultValue: false, description: 'Exécuter le navigateur en mode headless (sans interface graphique)')
        string(name: 'URL_GRID', description: 'URL du Selenium Grid. Laisser vide pour une exécution en local')
        choice(name: 'BROWSER', choices: ['CHROME', 'EDGE', 'RANDOM'], description: 'Navigateur à utiliser pour l\'exécution des tests')
        booleanParam(name: 'IMPORT_RESULTS', defaultValue: false, description: 'Importer les résultats d\'exécution dans Xray après les tests')
        string(name: 'EXEC_NAME', defaultValue: 'TNR Automatiques', description: 'Nom de l\'exécution de test importée dans Xray')
    }

    stages {

        stage('Export features') {
            steps {
                echo 'Exportation des features depuis Xray...'
                bat 'curl -H "Content-Type: application/json" -X GET -H "Authorization: Bearer %TOKEN%"  "https://xray.cloud.getxray.app/api/v1/export/cucumber?keys=%KEYS%" --output features.zip'
                bat 'if exist "src/test/resources/features" rmdir /s /q "src/test/resources/features"'
                bat 'mkdir "src/test/resources/features"'
                bat 'tar -xf features.zip -C src/test/resources/features'
                bat 'del features.zip'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Execution des tests Cucumber via Maven...'
                catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {
                    bat "mvn clean test -DurlGrid=%URL_GRID% -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS}"
                }
            }
        }
    }

    post {
        always {
            script {
                if (params.IMPORT_RESULTS) {
                    echo 'Importation des résultats d\'exécution vers Xray...'
                    bat ' curl -H "Content-Type: application/json" -X POST -H "Authorization: Bearer %TOKEN%"  --data @"target/cucumber.json" https://xray.cloud.getxray.app/api/v2/import/execution/cucumber
'
                } else {
                    echo 'Import des résultats ignoré ⏭️'
                }
            }
        }

        success {
            echo 'Tests exécutés avec succès ✅'
        }

        unstable {
            echo 'Des tests ont échoué ⚠️'
        }

        failure {
            echo 'L\'exécution a échoué ❌'
        }
    }
}
