# Setting up variables
$tmpFile = "temp.txt"
$ignoredFiles = ".DS_Store", "secrets.json", "README.md", "node_modules", "output.txt", 
                "temp.txt", "tree_script.sh", "package-lock.json", "ignored_files", ".git", 
                ".env", ".gitignore", "*.class",".jar", "LICENSE" # Ignoring .class files and LICENSE file
$projectRoot = Get-Location

# Function to create tree structure
function Show-Tree {
    param (
        [string]$path, 
        [string[]]$ignore
    )
    
    # Custom script block to filter out ignored items
    $scriptBlock = {
        foreach ($item in $ignore) {
            if ($_.FullName -like $item) {
                return $false
            }
        }
        return $true
    }

    Get-ChildItem -Path $path -Recurse | Where-Object $scriptBlock | ForEach-Object {
        $indent = " " * ($_.FullName -replace [regex]::Escape($projectRoot), '').Split('\').Count
        "$indent$($_.Name)"
    }
}

# Output the structure of the project to a temp file
"## Structure of the project" | Set-Content -Path $tmpFile

# Capture and ignore patterns while getting tree structure
Show-Tree -path $projectRoot -ignore $ignoredFiles | Add-Content -Path $tmpFile

$filePathList = @()

# Find all files by excluding the directories and ignored files
Get-ChildItem -Path $projectRoot -Recurse | Where-Object {
    !$_.PSIsContainer -and ($ignoredFiles -notcontains $_.Name) -and ($_.Extension -ne '.class') -and ($_.Name -ne 'LICENSE')
} | ForEach-Object {
    $filePathList += $_.FullName
}

# Output the project code to the temp file
"`n## Project code:" | Add-Content -Path $tmpFile

# Append the content of each file to the temp file
foreach ($file in $filePathList) {
    "**$file**" | Add-Content -Path $tmpFile
    '```' | Add-Content -Path $tmpFile
    Get-Content -Path $file | Add-Content -Path $tmpFile
    "" | Add-Content -Path $tmpFile
    '```' | Add-Content -Path $tmpFile
    "" | Add-Content -Path $tmpFile
}

# Move the temp file to the output file
Move-Item -Path $tmpFile -Destination "output.txt" -Force




