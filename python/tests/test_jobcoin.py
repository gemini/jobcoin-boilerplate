#!/usr/bin/env python
import pytest
import re
from click.testing import CliRunner

from ..jobcoin import config
from .. import cli


@pytest.fixture
def response():
    import requests
    return requests.get('https://jobcoin.gemini.com/')


def test_content(response):
    assert 'Hello!' in response.content


def test_cli_basic():
    runner = CliRunner()
    result = runner.invoke(cli.main)
    assert result.exit_code == 0
    assert 'Welcome to the Jobcoin mixer' in result.output


def test_cli_creates_address():
    runner = CliRunner()
    address_create_output = runner.invoke(cli.main, input='1234,4321').output
    output_re = re.compile(
        r'You may now send Jobcoins to address [0-9a-zA-Z]{32}. '
        'They will be mixed and sent to your destination addresses.'
    )
    assert output_re.search(address_create_output) is not None
