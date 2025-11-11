# Script para reemplazar System.out/System.err por logger en todos los archivos Java

$files = Get-ChildItem -Path "src\main\java" -Recurse -Filter *.java | 
    Where-Object { (Get-Content $_.FullName -Raw) -match 'System\.(out|err)\.' }

$totalFiles = $files.Count
$processedFiles = 0

Write-Host "Encontrados $totalFiles archivos con System.out/System.err" -ForegroundColor Yellow

foreach ($file in $files) {
    $processedFiles++
    Write-Host "[$processedFiles/$totalFiles] Procesando: $($file.Name)" -ForegroundColor Cyan
    
    $content = Get-Content $file.FullName -Raw
    $modified = $false
    
    # Verificar si ya tiene el import de Logger
    $hasLoggerImport = $content -match 'import org\.slf4j\.Logger;'
    
    # Si no tiene logger, agregarlo
    if (-not $hasLoggerImport -and $content -match 'System\.(out|err)\.') {
        # Encontrar el paquete
        if ($content -match '(?sm)^(package\s+[^;]+;)(.*)$') {
            $packageLine = $matches[1]
            $restOfFile = $matches[2]
            
            # Buscar el nombre de la clase
            if ($restOfFile -match 'public\s+(class|interface|enum)\s+(\w+)') {
                $className = $matches[2]
                
                # Agregar imports después del package
                $loggerImports = "`n`nimport org.slf4j.Logger;`nimport org.slf4j.LoggerFactory;"
                
                # Buscar donde insertar el logger field
                if ($restOfFile -match '(?sm)^(.*?public\s+(class|interface|enum)\s+\w+[^{]*\{)(.*)$') {
                    $beforeClass = $matches[1]
                    $afterClassStart = $matches[3]
                    
                    # Agregar logger field
                    $loggerField = "`n    private static final Logger logger = LoggerFactory.getLogger($className.class);"
                    
                    $content = $packageLine + $loggerImports + "`n" + $beforeClass + $loggerField + $afterClassStart
                    $modified = $true
                }
            }
        }
    }
    
    # Reemplazar System.out.println con logger.info
    if ($content -match 'System\.out\.println') {
        $content = $content -replace 'System\.out\.println\("([^"]+)"\);', 'logger.info("$1");'
        $content = $content -replace 'System\.out\.println\(([^)]+)\);', 'logger.info(String.valueOf($1));'
        $content = $content -replace 'System\.out\.print\("([^"]+)"\);', 'logger.info("$1");'
        $content = $content -replace 'System\.out\.printf\(', 'logger.info(String.format('
        $modified = $true
    }
    
    # Reemplazar System.err.println con logger.error
    if ($content -match 'System\.err\.println') {
        $content = $content -replace 'System\.err\.println\("([^"]+)"\);', 'logger.error("$1");'
        $content = $content -replace 'System\.err\.println\(([^)]+)\);', 'logger.error(String.valueOf($1));'
        $modified = $true
    }
    
    if ($modified) {
        Set-Content -Path $file.FullName -Value $content -Encoding UTF8 -NoNewline
        Write-Host "  ✓ Modificado" -ForegroundColor Green
    } else {
        Write-Host "  - Sin cambios" -ForegroundColor Gray
    }
}

Write-Host "`n✓ Proceso completado. $processedFiles archivos procesados." -ForegroundColor Green
