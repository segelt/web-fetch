# web-fetch

This project consumes [SPK REST API](https://ws.spk.gov.tr/) to obtain ETF information and to store them in local PostgreSQL database. This approach is used, rather than consuming the API directly from the front-end application, since the calls to this API may be rate limited.

This project can add new ETFs to the database, find missing price informations for current ETFs, and update price information for ETFs stored in the database.

Hibernate ORM is used to map database tables with project models.