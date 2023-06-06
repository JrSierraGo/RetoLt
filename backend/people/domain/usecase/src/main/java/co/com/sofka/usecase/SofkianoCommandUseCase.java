package co.com.sofka.usecase;

import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.ExtService;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.logging.Logger;


@RequiredArgsConstructor
public class SofkianoCommandUseCase {

    Logger log = Logger.getLogger(this.getClass().getName());

    private final SofkianoRepository sofkianoRepository;
    private final ExtService extService;

    private String archivo = "QVdTVGVtcGxhdGVGb3JtYXRWZXJzaW9uIDogJzIwMTAtMDktMDknClRyYW5zZm9ybTogQVdTOjpTZXJ2ZXJsZXNzLTIwMTYtMTAtMzEKRGVzY3JpcHRpb246IE1hbmFnZWQgU3RhY2sgZm9yIEFXUyBTQU0gQ0xJCgpQYXJhbWV0ZXJzOgogIFBKUHJlZml4OgogICAgRGVzY3JpcHRpb246IE5hbWUgUHJvamVjdAogICAgVHlwZTogU3RyaW5nCiAgICBEZWZhdWx0OiBiaWVuZXN0YXIKICAgIEFsbG93ZWRQYXR0ZXJuOiBeWzAtOWEtekEtWl0rKFswLTlhLXpBLVotXSpbMC05YS16QS1aXSkqJAogICAgQ29uc3RyYWludERlc2NyaXB0aW9uOiBBcnRpZmFjdHNCdWNrZXQgUzMgYnVja2V0IG5hbWUgY2FuIGluY2x1ZGUgbnVtYmVycywgbG93ZXJjYXNlIGxldHRlcnMsIHVwcGVyY2FzZSBsZXR0ZXJzLCBhbmQgaHlwaGVucyAoLSkuIEl0IGNhbm5vdCBzdGFydCBvciBlbmQgd2l0aCBhIGh5cGhlbiAoLSkuCiAgQXBwTmFtZToKICAgIERlc2NyaXB0aW9uOiAnQXBwbGljYXRpb24gbmFtZSwgdXNlZCBmb3IgdGhlIHJlcG9zaXRvcnkgYW5kIGNoaWxkIHN0YWNrIG5hbWUnCiAgICBUeXBlOiBTdHJpbmcKICAgIERlZmF1bHQ6IHBsYXRmb3JtCiAgU3RhZ2U6CiAgICBBbGxvd2VkVmFsdWVzOgogICAgICAtIHVzZXJzCiAgICAgIC0gZGV2CiAgICAgIC0gdGVzdAogICAgICAtIHByb2QKICAgIERlZmF1bHQ6IGRldgogICAgRGVzY3JpcHRpb246ID4tCiAgICAgIFNlbGVjdCB0aGUgZW52aXJvbm1lbnQgYWNjb3JkaW5nIHRvIHRoZSBuZWVkcyBvZiB0aGUgcHJvamVjdCwgdXNlZnVsIHRvCiAgICAgIHRyZWF0IHRoZSBleHBvcnQgb2YgY2xvdWRmb3JtYXRpb24gYW5kIHRhZ3MKICAgIFR5cGU6IFN0cmluZwogIFNOU1JvdXRlNTM6CiAgICBEZXNjcmlwdGlvbjogVGhlIEFSTiBvZiB0aGUgU05TIHRvcGljIHVzZWQgdG8gcmVxdWVzdCBhIENOQU1FIHJlY29yZC4KICAgIFR5cGU6IFN0cmluZwogICAgRGVmYXVsdDogZW1wdHkKICBEb21haW5FbnZpcm9ubWVudDoKICAgIERlc2NyaXB0aW9uOiBUaGUgQVJOIG9mIHRoZSBTTlMgdG9waWMgdXNlZCB0byByZXF1ZXN0IGEgQ05BTUUgcmVjb3JkLgogICAgVHlwZTogU3RyaW5nCiAgICBEZWZhdWx0OiBlbXB0eSAgCiAgTWFwcGluZzoKICAgIERlc2NyaXB0aW9uOiBUaGUgQVJOIG9mIHRoZSBTTlMgdG9waWMgdXNlZCB0byByZXF1ZXN0IGEgQ05BTUUgcmVjb3JkLgogICAgVHlwZTogU3RyaW5nCiAgICBEZWZhdWx0OiBlbXB0eSAgClJlc291cmNlczoKCgojIC0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSMKIyAgICBSb3V0ZTUzIC0gU3ViZG9tYWluIHBsYXRmb3JtYS1nbG9iYW50CiMgLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tIyMKCiAgZG9taW5pb3M6CiAgICBUeXBlOiBBV1M6OlNlcnZlcmxlc3M6OkFwcGxpY2F0aW9uCiAgICBQcm9wZXJ0aWVzOgogICAgICBMb2NhdGlvbjogImh0dHBzOi8vYXdzLWNuZi1yZXBvc2l0b3J5LWNvbXBlbnNhci5zMy5hbWF6b25hd3MuY29tL3BsYXRmb3JtLWRvbWluaW9zL3RlbXBsYXRlLnltbCIKICAgICAgUGFyYW1ldGVyczoKICAgICAgICBQSlByZWZpeDogIVJlZiBQSlByZWZpeAogICAgICAgIFN0YWdlOiAhUmVmIFN0YWdlCiAgICAgICAgQXBwTmFtZTogIVJlZiBBcHBOYW1lCiAgICAgICAgRG9tYWluRW52aXJvbm1lbnQ6ICFSZWYgRG9tYWluRW52aXJvbm1lbnQKICAgICAgICBNYXBwaW5nOiAhUmVmIE1hcHBpbmcKICAgICAgICBTTlNSb3V0ZTUzOiAhUmVmIFNOU1JvdXRlNTMKICAgICAgICBSZWNvcmROYW1lOiAicGxhdGFmb3JtYS1nbG9iYW50IgogICAgICAgIExvYWRCYWxhbmNlclByaW9yaXR5OiAiMTAxIgojIC0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSMKIyAgICBSb3V0ZTUzIC0gU3ViZG9tYWluIHBlcnNvbmFsaXphY2lvbgojIC0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLSMjCgogIGRvbWluaW9zMjoKICAgIFR5cGU6IEFXUzo6U2VydmVybGVzczo6QXBwbGljYXRpb24KICAgIFByb3BlcnRpZXM6CiAgICAgIExvY2F0aW9uOiAiaHR0cHM6Ly9hd3MtY25mLXJlcG9zaXRvcnktY29tcGVuc2FyLnMzLmFtYXpvbmF3cy5jb20vcGxhdGZvcm0tZG9taW5pb3MvdGVtcGxhdGUueW1sIgogICAgICBQYXJhbWV0ZXJzOgogICAgICAgIFBKUHJlZml4OiAhUmVmIFBKUHJlZml4CiAgICAgICAgU3RhZ2U6ICFSZWYgU3RhZ2UKICAgICAgICBBcHBOYW1lOiAhUmVmIEFwcE5hbWUKICAgICAgICBEb21haW5FbnZpcm9ubWVudDogIVJlZiBEb21haW5FbnZpcm9ubWVudAogICAgICAgIE1hcHBpbmc6ICFSZWYgTWFwcGluZwogICAgICAgIFNOU1JvdXRlNTM6ICFSZWYgU05TUm91dGU1MwogICAgICAgIFJlY29yZE5hbWU6ICJwZXJzb25hbGl6YWNpb24iCiAgICAgICAgTG9hZEJhbGFuY2VyUHJpb3JpdHk6ICIxMDIi";

    public Mono<Sofkiano> process(Sofkiano sofkiano){
        return Mono.justOrEmpty(Base64.getDecoder().decode(archivo))
                .map(this::base64)
                .doOnNext(a -> log.info(a))
                .thenReturn(sofkiano);
    }

    private String base64(byte[] ar){
        extService.fileYML(ar);
        return "ar";
    }

}
