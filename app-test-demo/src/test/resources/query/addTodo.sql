--This is example SQL query

INSERT INTO public.todo (id, deadline, description, is_completed)
VALUES ('1'::bigint, '2022-12-09 00:00:00'::timestamp without time zone, 'test'::character varying, false::boolean)
ON CONFLICT (id) DO NOTHING;