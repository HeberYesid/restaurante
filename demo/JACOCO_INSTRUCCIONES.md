# JaCoCo - Instrucciones de Cobertura de Código

## ✅ JaCoCo Implementado Exitosamente

JaCoCo (Java Code Coverage) está ahora configurado en el proyecto y genera reportes de cobertura automáticamente al ejecutar los tests.

## 📊 Cómo Generar el Reporte de Cobertura

### Opción 1: Ejecutar tests con cobertura (Recomendado)
```powershell
cd "C:\Users\HeberYesid\Desktop\restaurante\demo"
mvn clean test
```

### Opción 2: Solo generar reporte (si ya ejecutaste tests)
```powershell
mvn jacoco:report
```

### Opción 3: Ejecutar tests y validar umbrales de cobertura
```powershell
mvn clean test jacoco:check
```

## 📁 Ubicación de los Reportes

Después de ejecutar `mvn test`, los reportes se generan en:

- **Reporte HTML (visualizar en navegador):**
  ```
  target/site/jacoco/index.html
  ```
  
- **Reporte XML (para integración con CI/CD):**
  ```
  target/site/jacoco/jacoco.xml
  ```
  
- **Reporte CSV (para análisis en Excel):**
  ```
  target/site/jacoco/jacoco.csv
  ```

- **Archivo de ejecución (datos binarios):**
  ```
  target/jacoco.exec
  ```

## 🌐 Abrir Reporte HTML en Navegador

### Desde PowerShell:
```powershell
Start-Process "C:\Users\HeberYesid\Desktop\restaurante\demo\target\site\jacoco\index.html"
```

### Desde VS Code:
1. Navega a `target/site/jacoco/index.html`
2. Clic derecho → "Reveal in File Explorer"
3. Doble clic en `index.html`

## 📈 Configuración Actual de JaCoCo

### Umbrales de Cobertura Configurados:
- **Cobertura de líneas (LINE):** Mínimo 50%
- **Cobertura de branches (BRANCH):** Mínimo 40%

Si la cobertura cae por debajo de estos umbrales, el build fallará en la fase `jacoco:check`.

### Para ajustar umbrales:
Edita `pom.xml` en la sección del plugin JaCoCo:
```xml
<limit>
    <counter>LINE</counter>
    <value>COVEREDRATIO</value>
    <minimum>0.50</minimum>  <!-- Cambia este valor -->
</limit>
```

## 🎯 Estado Actual del Proyecto

Última ejecución de tests:
- ✅ **Tests ejecutados:** 58
- ✅ **Fallos:** 0
- ✅ **Errores:** 0
- ✅ **Omitidos:** 0
- ✅ **Clases analizadas:** 103

## 🔍 Interpretando el Reporte

### Indicadores de color en el reporte HTML:
- 🟢 **Verde:** Cobertura alta (>80%)
- 🟡 **Amarillo:** Cobertura media (50-80%)
- 🔴 **Rojo:** Cobertura baja (<50%)

### Métricas principales:
- **Instructions (C0):** Instrucciones bytecode ejecutadas
- **Branches (C1):** Decisiones if/switch cubiertas
- **Lines:** Líneas de código ejecutadas
- **Methods:** Métodos invocados
- **Classes:** Clases cargadas

## 🚀 Integración con CI/CD

### GitHub Actions (ejemplo):
```yaml
name: Tests con Cobertura

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Run tests with coverage
        run: mvn clean test
      - name: Upload coverage to Codecov
        uses: codecov/codecov-action@v3
        with:
          files: ./target/site/jacoco/jacoco.xml
```

## 📝 Comandos Útiles

### Limpiar reportes anteriores:
```powershell
mvn clean
```

### Ejecutar solo tests unitarios:
```powershell
mvn test -Dtest=*Test
```

### Ejecutar tests con verbose:
```powershell
mvn test -X
```

### Ver árbol de dependencias:
```powershell
mvn dependency:tree
```

## ⚙️ Configuración Avanzada

### Excluir clases del reporte:
Añade en `pom.xml` dentro de `<configuration>` del plugin JaCoCo:
```xml
<excludes>
    <exclude>com/example/Main.class</exclude>
    <exclude>**/*Test.class</exclude>
</excludes>
```

### Generar reporte agregado de múltiples módulos:
```xml
<execution>
    <id>report-aggregate</id>
    <phase>verify</phase>
    <goals>
        <goal>report-aggregate</goal>
    </goals>
</execution>
```

## 🐛 Troubleshooting

### Problema: "No se genera el reporte"
**Solución:** Asegúrate de ejecutar `mvn clean test` (no solo `mvn test`)

### Problema: "Advertencias de Java agent"
**Solución:** Es normal con Mockito + JaCoCo. Puedes ignorarlas o agregar `-XX:+EnableDynamicAgentLoading`

### Problema: "Build falla por cobertura baja"
**Solución:** Omite la verificación temporalmente:
```powershell
mvn test -Djacoco.skip=true
```

## 📚 Recursos Adicionales

- [Documentación oficial JaCoCo](https://www.jacoco.org/jacoco/trunk/doc/)
- [JaCoCo Maven Plugin](https://www.jacoco.org/jacoco/trunk/doc/maven.html)
- [Ejemplos de configuración](https://github.com/jacoco/jacoco/tree/master/org.jacoco.examples)

---

**Última actualización:** Noviembre 7, 2025
**Versión JaCoCo:** 0.8.11
