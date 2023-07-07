const {
    loadFixture,
} = require("@nomicfoundation/hardhat-network-helpers");
const { expect } = require("chai");

describe("Token", function () {
    async function deployContract() {
        const [owner, user1] = await ethers.getSigners();
        const Contract = await ethers.getContractFactory("Token");
        const name = "test token";
        const symbol = "tts";
        const contract = await Contract.deploy(name, symbol);
        return { contract, name, symbol, owner, user1, };
    }
    describe("Deployment", function () {
        it("Check name and symbol", async function () {
            const { contract, name, symbol, owner, user1 } = await loadFixture(deployContract);
            expect(await contract.name()).to.equal(name);
            expect(await contract.symbol()).to.equal(symbol);
        });
        it("mint and burn", async function () {
            const { contract, name, symbol, owner, user1 } = await loadFixture(deployContract);
            let mintAmount = 10000;
            let burnAmount = 5000;
            expect(await contract.balanceOf(user1.address)).to.equal(0);
            await contract.mint(user1.address, mintAmount);
            expect(await contract.balanceOf(user1.address)).to.equal(mintAmount);
            await contract.burn(user1.address, burnAmount);
            expect(await contract.balanceOf(user1.address)).to.equal(mintAmount - burnAmount);
        });
    });
});
