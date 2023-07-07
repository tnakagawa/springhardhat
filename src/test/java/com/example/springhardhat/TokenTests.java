package com.example.springhardhat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import com.example.springhardhat.contracts.Token;

public class TokenTests {

    @Test
    void checkNameAndSymbol() throws Exception {
        // デプロイ
        String url = "http://127.0.0.1:8545/";
        Web3j web3j = Web3j.build(new HttpService(url));
        Credentials credentials = Credentials
                .create("0x0000000000000000000000000000000000000000000000000000000000000001");
        BigInteger gasPrice = BigInteger.valueOf(1000000000);
        BigInteger gasLimit = BigInteger.valueOf(30000000);
        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
        // 名称とシンボル
        String name = "java test coin";
        String symbol = "jtc";
        Token token = Token.deploy(web3j, credentials, contractGasProvider, name, symbol).send();
        assertEquals(token.name().send(), name);
        assertEquals(token.symbol().send(), symbol);
    }

    @Test
    void checkMintAndBurn() throws Exception {
        // デプロイ
        String url = "http://127.0.0.1:8545/";
        Web3j web3j = Web3j.build(new HttpService(url));
        Credentials credentials = Credentials
                .create("0x0000000000000000000000000000000000000000000000000000000000000001");
        BigInteger gasPrice = BigInteger.valueOf(1000000000);
        BigInteger gasLimit = BigInteger.valueOf(30000000);
        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
        String name = "java test coin";
        String symbol = "jtc";
        Token token = Token.deploy(web3j, credentials, contractGasProvider, name, symbol).send();
        String contractAddress = token.getContractAddress();
        // トークンの発行と破棄
        Token token2 = Token.load(contractAddress, web3j, credentials, contractGasProvider);
        Credentials credentials2 = Credentials
                .create("0x0000000000000000000000000000000000000000000000000000000000000002");
        BigInteger mintValue = BigInteger.valueOf(100000);
        BigInteger burnValue = BigInteger.valueOf(40000);
        BigInteger balanceOf1 = token2.balanceOf(credentials2.getAddress()).send();
        assertEquals(balanceOf1, BigInteger.ZERO);
        token2.mint(credentials2.getAddress(), mintValue).send();
        BigInteger balanceOf2 = token2.balanceOf(credentials2.getAddress()).send();
        assertEquals(balanceOf2, mintValue);
        token2.burn(credentials2.getAddress(), burnValue).send();
        BigInteger balanceOf3 = token2.balanceOf(credentials2.getAddress()).send();
        assertEquals(balanceOf3, mintValue.subtract(burnValue));
    }

}
