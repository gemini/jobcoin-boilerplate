import sys
sys.exit('\n!!! Please update config.py with your personal Jobcoin URL and '
         'remove this message.\n')

# Replace the URL below
API_BASE_URL = 'https://jobcoin.projecticeland.net/changeme/api'
API_ADDRESS_URL = '{}/addresses'.format(API_BASE_URL)
API_TRANSACTIONS_URL = '{}/transactions'.format(API_BASE_URL)
