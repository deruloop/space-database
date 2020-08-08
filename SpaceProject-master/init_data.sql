--
-- Data for Name: satellite; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.satellite (id, name, first_obs, last_obs) FROM stdin;
1	Herschel	2009-07-10	2013-06-17
2	Spitzer	2003-12-18	2009-05-15
\.

--
-- Name: satellite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.satellite_id_seq', 2, true);

--
-- Data for Name: instrument; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.instrument (id, name) FROM stdin;
1	PACS
2	SPIRE
3	IRAC
4	MIPS
\.

--
-- Name: instrument_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.instrument_id_seq', 4, true);

--
-- Data for Name: ins_sat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ins_sat (satellite, instrument) FROM stdin;
1	1
1	2
2	3
2	4
\.

--
-- Data for Name: band; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.band (id, value) FROM stdin;
1	70.0
2	160.0
3	250.0
4	350.0
5	500.0
6	3.6
7	4.5
8	5.8
9	8.0
10	24.0
\.

--
-- Name: band_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.band_id_seq', 10, true);
--
-- Data for Name: ins_band; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ins_band (instrument, band) FROM stdin;
1	1
1	2
2	3
2	4
2	5
3	6
3	7
3	8
3	9
4	10
\.

--
-- Data for Name: space_agency; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.space_agency (id, name, foundation, headquarter, manager, num_employees) FROM stdin;
1	ESA	1975-02-23	Paris	Johann-Dietrich Worner	2200
2	NASA	1958-07-29	Washington, District of Columbia	Kirk Shireman	6450
\.

--
-- Name: space_agency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.space_agency_id_seq', 2, true);


COPY public.ag_sat (agency, satellite) FROM stdin;
1	1
2	2
\.

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, name, surname, pass, e_mail, type) FROM stdin;
spaceproject	cristiano	calicchia	11e0eed8d3696c0a632f822df385ab3c	cris.cali@hotmail.com	t
thegabrielesa	gabriele	sanelli	5f4dcc3b5aa765d61d8327deb882cf99	gabriele.sanelli@gmail.com	t
flaxalf	flavio	scaccia	7f222bc7c60cb391ec9c0a11e50dd46e	flavio.scaccia@gmail.com	t
rossimario	mario	rossi	4e3bef8abe5750f426c06ace27285197	rossi.mario@tiscali.it	f
\.
