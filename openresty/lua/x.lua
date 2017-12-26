
local math = require('math')
local string = require("string")
local table = require("table")
local cjson = require("cjson")

require "x_base_class"

x = class(base_type)


function x:sayhi()


    print("\n\n\n\nx.sayhi");
    -- shared dictionary
    local shared_dict = ngx.shared.shared_dict;
    -- client ip
    local remote_addr = ngx.var.remote_addr;
    --
    local http_host = ngx.var.http_host;
    --
    local http_user_agent = ngx.var.http_user_agent;

    --require "xutils"
    --local xutils = xutils.new();
    
    --
    if shared_dict:get(remote_addr) == nil then
        local t = {remote_addr=remote_addr, method='xu', times=0};
        --local succ, err, forcible = shared_dict:set(remote_addr, xutils:table2string(t));
        local succ, err, forcible = shared_dict:set(remote_addr, cjson.encode(t));        
    end

    --local access = xutils:string2table( shared_dict:get(remote_addr) );
    local access = cjson.decode( shared_dict:get(remote_addr) );    
    if access == nil then
        print("access is nil");
        return;
    else
        for key, value in pairs(access) do
            print(key, ":", value);
        end
    end

    access['times'] = access['times'] + 1;
    -- local succ, err, forcible = shared_dict:set(remote_addr, xutils:table2string(access));
    local succ, err, forcible = shared_dict:set(remote_addr, cjson.encode(access));
    
    print('\n@@@@@@@@@@@@@@@@--->>', access['times'], ", " , succ, err, forcible);
    if access['times'] >= 2 then
        local ups_from = shared_dict:get(http_host);
        shared_dict:set(http_host, ups);
        shared_dict:delete(remote_addr);
        ngx.var.target = '127.0.0.1:8082/examples'
        return
    else
        print('else times=', access['times'], "\n");
    end


    local key = ngx.var.http_user_agent;
    if not key then
        ngx.log(ngx.ERR, "no user-agent found");
        return ngx.exit(400);
    end
    local host = "127.0.0.1:8081";

    ngx.log(ngx.INFO, "go to 163...?");

--ngx.var.target = '127.0.0.1:8081/examples';
end
