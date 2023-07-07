@echo off
@REM https://github.com/ethereum/solidity/releases
set SOLC=C:\tools\solc\bin\solc-windows.exe
@REM https://github.com/web3j/web3j-cli/releases
set WEB3J=C:\tools\web3j-1.4.2\bin\web3j.bat
set CONTRACT_NAME=Token
set JAVA_PACKAGE=com.example.springhardhat.contracts

call %SOLC% --version
call %WEB3J% -version

pushd %~dp0

call rmdir /s /q .\out

call %SOLC% ./contracts/%CONTRACT_NAME%.sol --bin --abi --optimize -o ./out --base-path ./contracts --include-path ./node_modules/

call %WEB3J% generate solidity -b ./out/%CONTRACT_NAME%.bin -a ./out/%CONTRACT_NAME%.abi -o ./src/main/java -p %JAVA_PACKAGE%