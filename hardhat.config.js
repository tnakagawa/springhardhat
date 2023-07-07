require("@nomicfoundation/hardhat-toolbox");

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: "0.8.18",
  networks: {
    hardhat: {
      accounts: [
        {
          privateKey:
            "0x0000000000000000000000000000000000000000000000000000000000000001",
          balance: "10000000000000000000000",
        },
        {
          privateKey:
            "0x0000000000000000000000000000000000000000000000000000000000000002",
          balance: "10000000000000000000000",
        },
        {
          privateKey:
            "0x0000000000000000000000000000000000000000000000000000000000000003",
          balance: "10000000000000000000000",
        },
        {
          privateKey:
            "0x0000000000000000000000000000000000000000000000000000000000000004",
          balance: "10000000000000000000000",
        },
      ],
    },
  },

};
