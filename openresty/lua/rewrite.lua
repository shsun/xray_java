--[[
很多外部客户端请求JSONP接口时，都会包含一个时间戳类似的参数，从而导致Nginx proxy缓存无法命中（因为无法忽略指定的HTTP参数）。
下面的 规则删除了时间戳参数，使得Nginx可以缓存upstream server的响应内容，减轻后端服务器的负载。
]]


local cjson = require("cjson");


if ngx.var.args ~= nil then
    -- /some_request?_=1346491660 becomes /some_request
    local fixed_args, count = ngx.re.sub( ngx.var.args, "&?_=[0-9]+", "" );
    if count > 0 then
        return ngx.exec(ngx.var.uri, fixed_args);
    end
end


if ngx.var.request_uri ~= ngx.var.constant_location then
    return constant_default_upstream;
end

local upstream_addr_str = nil;
if nil ~= ngx.var.upstream_addr then
    upstream_addr_str = " from "..ngx.var.upstream_addr;
else
    upstream_addr_str = "";
end

local upstreams = {
    "www.shsun8081.com:8081",
    "www.shsun8082.com:8082"
};

local m = math.random( #upstreams );
ngx.log(ngx.ERR, 'rewrite @@@@@@@@@@@@@@@@---m>>'..m);
ngx.var.res = "127.0.0.1:8082";
--upstreams[ m ] 


ngx.log(ngx.ERR, 'rewrite @@@@@@@@@@@@@@@@---uas>>'..upstream_addr_str);


