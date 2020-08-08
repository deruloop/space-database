--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.13
-- Dumped by pg_dump version 9.5.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ag_sat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ag_sat (
    agency integer NOT NULL,
    satellite integer NOT NULL
);


ALTER TABLE public.ag_sat OWNER TO postgres;

--
-- Name: band; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.band (
    id integer NOT NULL,
    value numeric(4,1) NOT NULL
);


ALTER TABLE public.band OWNER TO postgres;

--
-- Name: band_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.band_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.band_id_seq OWNER TO postgres;

--
-- Name: band_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.band_id_seq OWNED BY public.band.id;


--
-- Name: boundary; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.boundary (
    filament_id integer NOT NULL,
    filament_name character varying(64) NOT NULL,
    g_pos integer NOT NULL
);


ALTER TABLE public.boundary OWNER TO postgres;

--
-- Name: branch; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.branch (
    id integer NOT NULL,
    type character(1) NOT NULL,
    filament_id integer,
    filament_name character varying(64),
    CONSTRAINT branch_type_check CHECK (((type = 'B'::bpchar) OR (type = 'S'::bpchar)))
);


ALTER TABLE public.branch OWNER TO postgres;

--
-- Name: branch_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.branch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.branch_id_seq OWNER TO postgres;

--
-- Name: branch_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.branch_id_seq OWNED BY public.branch.id;


--
-- Name: branch_point; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.branch_point (
    branch integer NOT NULL,
    g_pos integer NOT NULL,
    prog_num integer,
    flux double precision
);


ALTER TABLE public.branch_point OWNER TO postgres;

--
-- Name: fil_sat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fil_sat (
    filament_id integer NOT NULL,
    filament_name character varying(64) NOT NULL,
    satellite integer NOT NULL
);


ALTER TABLE public.fil_sat OWNER TO postgres;

--
-- Name: filament; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.filament (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    total_flux double precision,
    mean_dens double precision,
    mean_temp double precision,
    ellipticity double precision,
    contrast double precision
);


ALTER TABLE public.filament OWNER TO postgres;

--
-- Name: g_position; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.g_position (
    id integer NOT NULL,
    g_lat double precision NOT NULL,
    g_long double precision NOT NULL
);


ALTER TABLE public.g_position OWNER TO postgres;

--
-- Name: g_position_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.g_position_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.g_position_id_seq OWNER TO postgres;

--
-- Name: g_position_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.g_position_id_seq OWNED BY public.g_position.id;


--
-- Name: ins_band; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ins_band (
    instrument integer NOT NULL,
    band integer NOT NULL
);


ALTER TABLE public.ins_band OWNER TO postgres;

--
-- Name: ins_sat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ins_sat (
    satellite integer NOT NULL,
    instrument integer NOT NULL
);


ALTER TABLE public.ins_sat OWNER TO postgres;

--
-- Name: instrument; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.instrument (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);


ALTER TABLE public.instrument OWNER TO postgres;

--
-- Name: instrument_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.instrument_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instrument_id_seq OWNER TO postgres;

--
-- Name: instrument_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.instrument_id_seq OWNED BY public.instrument.id;


--
-- Name: satellite; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.satellite (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    first_obs date,
    last_obs date
);


ALTER TABLE public.satellite OWNER TO postgres;

--
-- Name: satellite_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.satellite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.satellite_id_seq OWNER TO postgres;

--
-- Name: satellite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.satellite_id_seq OWNED BY public.satellite.id;


--
-- Name: space_agency; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.space_agency (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    foundation date,
    headquarter character varying(64),
    manager character varying(64),
    num_employees integer
);


ALTER TABLE public.space_agency OWNER TO postgres;

--
-- Name: space_agency_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.space_agency_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.space_agency_id_seq OWNER TO postgres;

--
-- Name: space_agency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.space_agency_id_seq OWNED BY public.space_agency.id;


--
-- Name: star; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.star (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    type character varying(64),
    flux double precision,
    g_pos integer,
    CONSTRAINT star_type_check CHECK ((((type)::text = 'UNBOUND'::text) OR ((type)::text = 'PROTOSTELLAR'::text) OR ((type)::text = 'PRESTELLAR'::text)))
);


ALTER TABLE public.star OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id character varying(64) NOT NULL,
    name character varying(64),
    surname character varying(64),
    pass character varying(32) NOT NULL,
    e_mail character varying(64),
    type boolean NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band ALTER COLUMN id SET DEFAULT nextval('public.band_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch ALTER COLUMN id SET DEFAULT nextval('public.branch_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.g_position ALTER COLUMN id SET DEFAULT nextval('public.g_position_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instrument ALTER COLUMN id SET DEFAULT nextval('public.instrument_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.satellite ALTER COLUMN id SET DEFAULT nextval('public.satellite_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.space_agency ALTER COLUMN id SET DEFAULT nextval('public.space_agency_id_seq'::regclass);


--
-- Name: ag_sat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ag_sat
    ADD CONSTRAINT ag_sat_pkey PRIMARY KEY (agency, satellite);


--
-- Name: band_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.band
    ADD CONSTRAINT band_pkey PRIMARY KEY (id);


--
-- Name: boundary_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boundary
    ADD CONSTRAINT boundary_pkey PRIMARY KEY (filament_id, filament_name, g_pos);


--
-- Name: branch_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch
    ADD CONSTRAINT branch_pkey PRIMARY KEY (id);


--
-- Name: branch_point_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch_point
    ADD CONSTRAINT branch_point_pkey PRIMARY KEY (branch, g_pos);


--
-- Name: fil_sat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fil_sat
    ADD CONSTRAINT fil_sat_pkey PRIMARY KEY (filament_id, filament_name, satellite);


--
-- Name: filament_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.filament
    ADD CONSTRAINT filament_pkey PRIMARY KEY (id, name);


--
-- Name: g_position_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.g_position
    ADD CONSTRAINT g_position_pkey PRIMARY KEY (id);


--
-- Name: ins_band_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ins_band
    ADD CONSTRAINT ins_band_pkey PRIMARY KEY (instrument, band);


--
-- Name: ins_sat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ins_sat
    ADD CONSTRAINT ins_sat_pkey PRIMARY KEY (satellite, instrument);


--
-- Name: instrument_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.instrument
    ADD CONSTRAINT instrument_pkey PRIMARY KEY (id);


--
-- Name: satellite_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.satellite
    ADD CONSTRAINT satellite_pkey PRIMARY KEY (id);


--
-- Name: space_agency_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.space_agency
    ADD CONSTRAINT space_agency_pkey PRIMARY KEY (id);


--
-- Name: star_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.star
    ADD CONSTRAINT star_pkey PRIMARY KEY (id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: ag_sat_agency_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ag_sat
    ADD CONSTRAINT ag_sat_agency_fkey FOREIGN KEY (agency) REFERENCES public.space_agency(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ag_sat_satellite_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ag_sat
    ADD CONSTRAINT ag_sat_satellite_fkey FOREIGN KEY (satellite) REFERENCES public.satellite(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boundary_filament_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boundary
    ADD CONSTRAINT boundary_filament_id_fkey FOREIGN KEY (filament_id, filament_name) REFERENCES public.filament(id, name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: boundary_g_pos_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.boundary
    ADD CONSTRAINT boundary_g_pos_fkey FOREIGN KEY (g_pos) REFERENCES public.g_position(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: branch_filament_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch
    ADD CONSTRAINT branch_filament_id_fkey FOREIGN KEY (filament_id, filament_name) REFERENCES public.filament(id, name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: branch_point_branch_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch_point
    ADD CONSTRAINT branch_point_branch_fkey FOREIGN KEY (branch) REFERENCES public.branch(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: branch_point_g_pos_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branch_point
    ADD CONSTRAINT branch_point_g_pos_fkey FOREIGN KEY (g_pos) REFERENCES public.g_position(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fil_sat_filament_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fil_sat
    ADD CONSTRAINT fil_sat_filament_id_fkey FOREIGN KEY (filament_id, filament_name) REFERENCES public.filament(id, name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fil_sat_satellite_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fil_sat
    ADD CONSTRAINT fil_sat_satellite_fkey FOREIGN KEY (satellite) REFERENCES public.satellite(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ins_band_band_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ins_band
    ADD CONSTRAINT ins_band_band_fkey FOREIGN KEY (band) REFERENCES public.band(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ins_band_instrument_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ins_band
    ADD CONSTRAINT ins_band_instrument_fkey FOREIGN KEY (instrument) REFERENCES public.instrument(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ins_sat_instrument_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ins_sat
    ADD CONSTRAINT ins_sat_instrument_fkey FOREIGN KEY (instrument) REFERENCES public.instrument(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: ins_sat_satellite_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ins_sat
    ADD CONSTRAINT ins_sat_satellite_fkey FOREIGN KEY (satellite) REFERENCES public.satellite(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: star_g_pos_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.star
    ADD CONSTRAINT star_g_pos_fkey FOREIGN KEY (g_pos) REFERENCES public.g_position(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

