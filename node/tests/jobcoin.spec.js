#!/usr/bin/env node
"use strict";
const expect = require("chai").expect;
const utils = require("../utils");

describe("utils", () => {
  it("should exist", () => {
    expect(utils).to.be.ok;
  });

  it("generateDepositAddress generates a string with 8 characters", () => {
    const depositAddress = utils.generateDepositAddress();
    expect(typeof depositAddress).to.equal("string");
    expect(depositAddress).to.have.length(8);
  });
});
